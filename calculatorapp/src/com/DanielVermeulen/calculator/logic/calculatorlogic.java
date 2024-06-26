package com.DanielVermeulen.calculator.logic;

import java.util.Stack;

public class calculatorlogic {
    private StringBuilder current_input;
    private String expression;
    private double result;

    public calculatorlogic() {
        current_input = new StringBuilder();
        expression = "";
        result = 0;
    }

    public void input_number(char number) {
        current_input.append(number);
        expression = current_input.toString();
    }

    public void input_operator(char operator) {
        if (current_input.length() > 0) {
            current_input.append(operator);
            expression = current_input.toString();
        }
    }

    public void calculate() {
        try {
            result = evaluate_expression(expression);
            current_input.setLength(0);
            current_input.append(result);
        } catch (Exception e) {
            current_input.setLength(0);
            current_input.append("Error");
        }
    }

    public void clear() {
        current_input.setLength(0);
        expression = "";
        result = 0;
    }

    public double get_result() {
        return result;
    }

    public String get_current_input() {
        return current_input.toString();
    }

    public String get_expression() {
        return expression;
    }

    private double evaluate_expression(String expr) throws Exception {
        Stack<Double> values = new Stack<>();
        Stack<Character> ops = new Stack<>();
        int i = 0;

        while (i < expr.length()) {
            if (expr.charAt(i) == ' ') {
                i++;
                continue;
            }

            if (expr.charAt(i) >= '0' && expr.charAt(i) <= '9') {
                StringBuilder sbuf = new StringBuilder();
                while (i < expr.length() && expr.charAt(i) >= '0' && expr.charAt(i) <= '9') {
                    sbuf.append(expr.charAt(i++));
                }
                values.push(Double.parseDouble(sbuf.toString()));
                continue;
            }

            if (expr.charAt(i) == '(') {
                ops.push(expr.charAt(i));
            } else if (expr.charAt(i) == ')') {
                while (ops.peek() != '(') {
                    values.push(apply_operator(ops.pop(), values.pop(), values.pop()));
                }
                ops.pop();
            } else if (expr.charAt(i) == '+' || expr.charAt(i) == '-' || expr.charAt(i) == '*'
                    || expr.charAt(i) == '/') {
                while (!ops.empty() && has_precedence(expr.charAt(i), ops.peek())) {
                    values.push(apply_operator(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(expr.charAt(i));
            }
            i++;
        }

        while (!ops.empty()) {
            values.push(apply_operator(ops.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private boolean has_precedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    private double apply_operator(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
        }
        return 0;
    }
}

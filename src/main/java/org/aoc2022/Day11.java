package org.aoc2022;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day11 {
    public final String FILE_DIR = "./src/main/resources/day11.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day11() {
        try {
            scanner = new Scanner(file);
        } catch (Exception ignored) {
        }
    }

    private List<Monkey> parseMonkeyList(File file) throws FileNotFoundException {
        scanner = new Scanner(file);

        List<Monkey> monkeys = new ArrayList<>();

        Monkey current = new Monkey();
        String operandOne = "";
        String operAction = "";
        String operandTwo = "";
        String testOperand = "";
        int monkeyThrowTrue = 0;
        int monkeyThrowFalse = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.contains("Starting items:")) {
                Deque<Integer> deque = new LinkedList<>();
                String[] tokens = line.split(":")[1].split(",");

                for (String token : tokens) {
                    deque.add(Integer.parseInt(token.trim()));
                }
                current.setDeque(deque);
            } else if (line.contains("Operation:")) {
                String[] tokens = line.split(" ");
                operandOne = tokens[5].trim();
                operAction = tokens[6].trim();
                operandTwo = tokens[7].trim();

                current.setOperandOne(operandOne);
                current.setOperAction(operAction);
                current.setOperandTwo(operandTwo);
            } else if (line.contains("Test:")) {
                String[] tokens = line.split(" ");
                testOperand = tokens[5].trim();

                current.setTestOperand(Integer.parseInt(testOperand));
            } else if (line.contains("true: throw to")) {
                String[] tokens = line.split(" ");
                Integer mtt = Integer.parseInt(tokens[9]);
                current.setMonkeyThrowTrue(mtt);
            } else if (line.contains("false: throw to")) {
                String[] tokens = line.split(" ");
                Integer mtf = Integer.parseInt(tokens[9]);
                current.setMonkeyThrowFalse(mtf);
            } else if (line.isBlank()) {
                monkeys.add(current);
                current = new Monkey();
                operandOne = "";
                operAction = "";
                operandTwo = "";
                testOperand = "";
                monkeyThrowTrue = 0;
                monkeyThrowFalse = 0;
            }
        }

        monkeys.add(current);
        current = new Monkey();
        operandOne = "";
        operAction = "";
        operandTwo = "";
        testOperand = "";
        monkeyThrowTrue = 0;
        monkeyThrowFalse = 0;

        return monkeys;
    }

    public void partOne() throws FileNotFoundException {

        List<Monkey> monkeys = parseMonkeyList(file);

        int ROUNDS = 20;

        for (int i = 0; i < ROUNDS; i++) {
            for (Monkey monkey : monkeys) {
                while (!monkey.getDeque().isEmpty()) {
                    monkeyDo(monkey, monkeys);
                    monkey.setActionCount(monkey.getActionCount() + 1);
                }

            }
        }

        monkeys.sort(Comparator.comparingInt(Monkey::getActionCount));

        int monkeyBusiness = monkeys.get(monkeys.size() - 1).getActionCount() *
                monkeys.get(monkeys.size() - 2).getActionCount();

        System.out.println(ROUNDS + " rounds: " + "Monkey business: " + monkeyBusiness);

        scanner.close();
    }

    private void monkeyDo(Monkey monkey, List<Monkey> monkeys) {
        Integer currentItem = monkey.getDeque().removeFirst();

        int op1 = currentItem;
        int op2;
        if (Objects.equals(monkey.operandTwo, "old")) {
            op2 = op1;
        } else {
            op2 = Integer.parseInt(monkey.operandTwo);
        }

        int worryLevel = 0;
        switch (monkey.operAction) {
            case "+": {
                worryLevel = op1 + op2;
                break;
            }
            case "-": {
                worryLevel = op1 - op2;
                break;
            }
            case "*": {
                worryLevel = op1 * op2;
                break;
            }
            case "/": {
                worryLevel = op1 / op2;
                break;
            }
        }

        worryLevel = worryLevel / 3;

        if (worryLevel % monkey.getTestOperand() == 0) {
            monkeys.get(monkey.monkeyThrowTrue).getDeque().addLast(worryLevel);
        } else {
            monkeys.get(monkey.monkeyThrowFalse).getDeque().addLast(worryLevel);
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private class Monkey {
        Deque<Integer> deque = new LinkedList<>();
        String operandOne;
        String operAction;
        String operandTwo;
        Integer testOperand;
        Integer monkeyThrowTrue;
        Integer monkeyThrowFalse;
        Integer actionCount = 0;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Monkey\n");
            sb.append("Items: " + deque.toString() + "\n");
            sb.append("Operation: " + operAction + " " + operandTwo + "\n");
            sb.append("Test divisible by " + testOperand + "\n");
            sb.append("True: " + monkeyThrowTrue + "\n");
            sb.append("False: " + monkeyThrowFalse + "\n");
            sb.append("Action count: " + actionCount + "\n");
            return sb.toString();
        }
    }

}
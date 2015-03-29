import java.util.Scanner;

/**
 * Determines whether an array of numbers can be manipulated using basic
 * arithmetic such that its result is equal to a specified number.
 *
 * @author Andrei Muntean
 */
public class NumberProblem
{
    private static int[] numbers;
    private static String[] operations;
    private static int result;

    private static boolean solve(int index, int currentResult)
    {
        if (index == numbers.length)
        {
            return currentResult == result;
        }
        else
        {
            if (solve(index + 1, currentResult + numbers[index]))
            {
                operations[index - 1] = " + ";

                return true;
            }
            else if (solve(index + 1, currentResult - numbers[index]))
            {
                operations[index - 1] = " - ";

                return true;
            }
            else if (solve(index + 1, currentResult * numbers[index]))
            {
                operations[index - 1] = ") * ";

                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public static boolean solve()
    {
        return solve(1, numbers[0]);
    }
 
    public static void main(String[] args)
    {
        try (Scanner scanner = new Scanner(System.in))
        {
            int numberCount;

            System.out.println("How many numbers would you like to input?");
            numberCount = scanner.nextInt();
            numbers = new int[numberCount];
            operations = new String[numberCount - 1];
            System.out.printf("Input %d numbers.%n", numberCount);

            while (numberCount > 0)
            {
                numbers[numbers.length - numberCount--] = scanner.nextInt();
            }

            System.out.println("What would you like the result to be?");
            result = scanner.nextInt();
        }
 
        // Determines whether the problem can be solved.
        if (solve())
        {
            // Outputs the solution.
            System.out.print(numbers[0]);

            for (int index = 0; index < operations.length; ++index)
            {
                System.out.printf("%s%d", operations[index], numbers[index + 1]);
            }

            System.out.printf(" = %d%n", result);
        }
        else
        {
            System.out.println("There is no solution to this problem.");
        }
    }
}
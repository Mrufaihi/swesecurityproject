package swesecurityproject;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FitnessRecommender {
    // Constants for input validation
    // input limit
    private static final int MAX_INPUT_LENGTH = 30;
     // valid medical history inputs
    private static final Set<String> VALID_MEDICAL_HISTORY = new HashSet<>(Arrays.asList(
        "severe chest pain", "stroke", "heart attack", 
        "severe asthma", "recent surgery", "severe joint pain", "none"));
    // valid fitness goal inputs
    private static final Set<String> VALID_FITNESS_GOALS = new HashSet<>(Arrays.asList(
        "weight loss", "muscle building", "improve flexibility", 
        "cardiovascular health", "stress relief"));
    // valid fitness level inputs
    private static final Set<String> VALID_FITNESS_LEVELS = new HashSet<>(Arrays.asList(
        "beginner", "intermediate", "advanced"
    ));

    private static String validateInput(String input, Set<String> validOptions, String fieldName) {
        try {
            // 1. Check for null or empty input
            if (input == null || input.trim().isEmpty()) {
                throw new SecurityException("Empty input not allowed for " + fieldName);
            }

            // 2. Check for buffer overflow - limit input length
            if (input.length() > MAX_INPUT_LENGTH) {
                throw new SecurityException(fieldName + " exceeds maximum length of " + MAX_INPUT_LENGTH + " characters");
            }

            // 3. Check for invalid characters
            if (!input.matches("^[a-zA-Z ]+$")) {
                throw new SecurityException("Invalid characters detected in " + fieldName);
            }

            // 4. Convert to lowercase for comparison
            String normalizedInput = input.toLowerCase().trim();

            // 5. Validate against allowed options
            if (!validOptions.contains(normalizedInput)) {
                throw new SecurityException("Invalid " + fieldName + " option");
            }

            return normalizedInput;

        } catch (SecurityException e) {
            // 4. Immediate error alert
            System.err.println("Security Error: " + e.getMessage());
            // 5. Immediate program exit on error
            System.exit(1);
            return null; // This line will never be reached due to System.exit
        }
    }

    public static void main(String[] args) {
        FitnessPlan.initFitnessPlans();
        
        // 6. Wrap all input operations in try-catch
        try (Scanner scanner = new Scanner(System.in)) {
            // Medical history check
            System.out.print("Do you have any medical conditions? (yes/no): ");
            String hasCondition = validateInput(
                scanner.nextLine(),
                new HashSet<>(Arrays.asList("yes", "no")), //array of yes or no as valid options
                "has condition"
            );
            
            if (hasCondition.equals("yes")) {
                System.out.print("###\nsevere chest pain\nstroke\nheart attack\nsevere asthma\nrecent surgery\nsevere joint pain \n###\n Please specify if you have had any of the above (or enter 'none' if not listed):");
                String medicalHistory = validateInput(
                    scanner.nextLine(),
                    VALID_MEDICAL_HISTORY,
                    "medical history"
                );

            // Modified condition handling
            if (medicalHistory.equals("none")) {
            // Proceed to fitness goals if condition is "none"
            System.out.println("non-severe condition, Proceeding with fitness plan recommendations...");
            } else {
            // Exit with warning for choosen medical condition
            System.out.println("## Important ## \nDue to your condition (" + medicalHistory + 
                             "), you must consult a healthcare professional before starting any fitness program.");
                return;
            }       

            };
            
            // Secure fitness goal input
            System.out.print("What is your primary fitness goal? (weight loss, muscle building, improve flexibility, cardiovascular health, stress relief) ");
            String fitnessGoal = validateInput(
                scanner.nextLine(),
                VALID_FITNESS_GOALS,
                "fitness goal"
            );
            
            // Secure fitness level input
            System.out.print("What is your current fitness level? (beginner, intermediate, advanced) ");
            String currentLevel = validateInput(
                scanner.nextLine(),
                VALID_FITNESS_LEVELS,
                "fitness level"
            );
            
            FitnessPlan recommendedPlan = FitnessPlan.getRecommendedPlan(fitnessGoal, currentLevel);
            
            if (recommendedPlan == null) {
                System.out.println("No suitable plan found");
                return;
            }

            int exerciseTime = recommendedPlan.getExerciseTime(currentLevel);
            
            System.out.println("Recommended Fitness Plan: " + recommendedPlan.planName);
            System.out.println("Required Weekly Exercise Time: " + exerciseTime + " minutes");
            
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            System.exit(1);
        }
    }
}
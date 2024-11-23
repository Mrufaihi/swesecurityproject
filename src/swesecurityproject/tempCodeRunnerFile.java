
// package swesecurityproject;

// import java.util.Scanner;

// public class FitnessRecommender {

//     public static void main(String[] args) {
        
//         FitnessPlan.initFitnessPlans();
        
//         Scanner scanner = new Scanner(System.in);

//         // ask user for medical history
//         System.out.print("Do you have any medical issues? (yes or no): ");
//         String medicalHistory = scanner.nextLine();

//         //if user has medical issues then cancel recommendtion & refer them to doctor        
//         if(medicalHistory.equalsIgnoreCase("Yes")){
//             System.out.println("Please contact a doctor for a fitness plan");
//             return;
//         }
        
//         // ask user for fitness goal
//         System.out.print("What is your primary fitness goal? (weight loss, muscle building, improve flexibility, cardiovascular health, stress Relief) ");
//         String fitnessGoal = scanner.nextLine();

//         // ask user for fitness level
//         System.out.print("What is your current fitness level? (beginner, intermediate, advanced) ");
//         String currentLevel = scanner.nextLine();
        
//         //call getRecommenededPlan method 
//         FitnessPlan recommendedPlan = FitnessPlan.getRecommendedPlan(fitnessGoal, currentLevel);
        
//         //if no recommended plan found
//         if (recommendedPlan==null){
//             System.out.println("No suitable plan found");
//             return;
//         }

//          //get exercise time 
//         int exerciseTime = recommendedPlan.getExerciseTime(currentLevel);
        
//         // display fitness plan
//         System.out.println("Recommended Fitness Plan: " + recommendedPlan.planName);
//         // display exercise time
//         System.out.println("Required Weekly Exercise Time: " + exerciseTime + " minutes");

//     }
    
// }

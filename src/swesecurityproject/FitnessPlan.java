// Salman Alwathanany 2240237
// ahmad alharthey 2140035
//Mohammed alzahrnai 2141257

package swesecurityproject;

import java.util.ArrayList;

public class FitnessPlan {
    
    protected String planName;
    protected int baseTime;
    private final static int BEGINNER = 1;
    private final static int INTERMEDIATE = 2;
    private final static int ADVANCED = 3;
    
    protected static ArrayList<FitnessPlan> fitnessPlans;
    
    protected static void initFitnessPlans(){
        if (fitnessPlans==null){
            fitnessPlans = new ArrayList<>();
            fitnessPlans.add(new FitnessPlan("Cardio",150));
            fitnessPlans.add(new FitnessPlan("Strength Training",120));
            fitnessPlans.add(new FitnessPlan("Flexibility",90));
            fitnessPlans.add(new FitnessPlan("HIIT (High-Intensity Interval Training)",90));
            fitnessPlans.add(new FitnessPlan("Yoga",120));
        }
    }
    
    private FitnessPlan(String planName,int baseTime){
        //prevent null as plan name
        if (planName==null){
            planName="";
        }
        this.planName = planName;
        this.baseTime = baseTime;
    }
    
    // Recomended plan function
    protected static FitnessPlan getRecommendedPlan(String goal, String level) {
        
        if (fitnessPlans==null||goal==null||level==null){
            return null;
        }
        
        int fitnessLevel=0;
        if (level.equalsIgnoreCase("Beginner")){
            fitnessLevel=BEGINNER;
        }
        else if (level.equalsIgnoreCase("Intermediate")){
            fitnessLevel=INTERMEDIATE;
        }
        else if (level.equalsIgnoreCase("Advanced")){
            fitnessLevel=ADVANCED;
        }
        
        if (fitnessLevel<BEGINNER||fitnessLevel>ADVANCED){
            return null;
        }
        
        // if weight loss + beginner = cardio
        if (goal.equalsIgnoreCase("Weight Loss")) {
            return fitnessPlans.get(0);
        // if weight muscle + intermediate = strength training
        } else if (goal.equalsIgnoreCase("Muscle Building") && fitnessLevel>=INTERMEDIATE) {
            return fitnessPlans.get(1);
        // if weight improve flexiblity + beginner = flexiblity    
        } else if (goal.equalsIgnoreCase("Improve Flexibility")) {
            return fitnessPlans.get(2);
            //if cardiovascular + advanced = HIIT
        } else if (goal.equalsIgnoreCase("Cardiovascular Health") && fitnessLevel==ADVANCED) {
            return fitnessPlans.get(3);
            //if stress+ beginner = YOGA
        } else if (goal.equalsIgnoreCase("Stress Relief")) {
            return fitnessPlans.get(4);
            //if none of above = no plan found
        } else {
            return null;
        }
    }
    
    //Exercise Time calculation function
    protected int getExerciseTime(String level) {
        
        if (level==null){
            return baseTime;
        }
        
        // IF Beginner then base+30 
        if (level.equalsIgnoreCase("Beginner")) {
            return baseTime + 30;
        // IF Beginner then base+20
        } else if (level.equalsIgnoreCase("Intermediate")) {
            return baseTime + 20;
        // IF advanced then base+10 
        } else if (level.equalsIgnoreCase("Advanced")) {
            return baseTime + 10;
            // if not properly provided return base
        }else{
            return baseTime;
        }
    }   
}

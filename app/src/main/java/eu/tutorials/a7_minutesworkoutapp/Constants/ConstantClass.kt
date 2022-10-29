package eu.tutorials.a7_minutesworkoutapp.Constants

import eu.tutorials.a7_minutesworkoutapp.Model.ExcerciseModel
import eu.tutorials.a7_minutesworkoutapp.R

class Constants {
    companion object {
        // The drawable images used here are added in the drawable folder.
        /**
         * Here we are adding all exercises to a single list with all the default values.
         * This method returns the ArrayList containing the instances of the ExcerciseModel
         */
        fun defaultExerciseList(): ArrayList<ExcerciseModel> {

            val exerciseList = ArrayList<ExcerciseModel>()

            val jumpingJacks = ExcerciseModel(
                    1,
                    "Jumping Jacks",
                    R.drawable.ic_jumping_jacks,
                    false,
                    false)
            exerciseList.add(jumpingJacks)

            val wallSit = ExcerciseModel(
                2,
                "Wall Sit",
                R.drawable.ic_wall_sit,
                false,
                false)
            // Adding into Array
            exerciseList.add(wallSit)

            val pushUp = ExcerciseModel(
                3,
                "Push Up",
                R.drawable.ic_push_up,
                false,
                false)
            exerciseList.add(pushUp)

            val abdominalCrunch = ExcerciseModel(
                    4,
                    "Abdominal Crunch",
                    R.drawable.ic_abdominal_crunch,
                    false,
                    false)
            exerciseList.add(abdominalCrunch)

            val stepUpOnChair = ExcerciseModel(
                    5,
                    "Step-Up onto Chair",
                    R.drawable.ic_step_up_onto_chair,
                    false,
                    false
                )
            exerciseList.add(stepUpOnChair)

            val squat = ExcerciseModel(
                6,
                "Squat",
                R.drawable.ic_squat,
                false,
                false)
            exerciseList.add(squat)

            val tricepDipOnChair = ExcerciseModel(
                    7,
                    "Tricep Dip On Chair",
                    R.drawable.ic_triceps_dip_on_chair,
                    false,
                    false
                )
            exerciseList.add(tricepDipOnChair)

            val plank = ExcerciseModel(
                8,
                "Plank",
                R.drawable.ic_plank,
                false,
                false)
            exerciseList.add(plank)

            val highKneesRunningInPlace = ExcerciseModel(
                    9,
                    "High Knees Running In Place",
                    R.drawable.ic_high_knees_running_in_place,
                    false,
                    false
                )
            exerciseList.add(highKneesRunningInPlace)

            val lunges = ExcerciseModel(
                10,
                "Lunges",
                R.drawable.ic_lunge,
                false,
                false)
            exerciseList.add(lunges)

            val pushupAndRotation = ExcerciseModel(
                    11,
                    "Push up and Rotation",
                    R.drawable.ic_push_up_and_rotation,
                    false,
                    false
                )
            exerciseList.add(pushupAndRotation)

            val sidePlank = ExcerciseModel(12,
                "Side Plank",
                R.drawable.ic_side_plank,
                false,
                false)
            exerciseList.add(sidePlank)
            // ExcerciseList is the ArrayList of the ExcerciseModel (data class) Instances.
            return exerciseList
        }
    }
}
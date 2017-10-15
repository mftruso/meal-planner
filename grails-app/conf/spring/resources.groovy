import com.trusowebdev.mealplanner.UserPasswordEncoderListener
import com.trusowebdev.mealplanner.UserPasswordEncoderListener
import com.trusowebdev.mealplanner.UserPasswordEncoderListener
// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener, ref('hibernateDatastore'))
    userPasswordEncoderListener(UserPasswordEncoderListener, ref('hibernateDatastore'))
    userPasswordEncoderListener(UserPasswordEncoderListener, ref('hibernateDatastore'))
}

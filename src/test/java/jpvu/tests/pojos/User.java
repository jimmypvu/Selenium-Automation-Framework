package jpvu.tests.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import framework.utils.DataType;
import framework.utils.DataGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    @Builder.Default private String firstName = DataGenerator.getRandomFor(DataType.FIRSTNAME);
    @Builder.Default private String lastName = DataGenerator.getRandomFor(DataType.LASTNAME);
    @Builder.Default private String email = DataGenerator.getRandomFor(DataType.EMAIL);
    @Builder.Default private String address = DataGenerator.getRandomFor(DataType.ADDRESS);
    @Builder.Default private String city = DataGenerator.getRandomFor(DataType.CITY);
    @Builder.Default private String state = "California";
    @Builder.Default private String country = "United States";
    @Builder.Default private String zip = DataGenerator.getRandomNumber(5);
    @Builder.Default private String username = DataGenerator.getRandomFor(DataType.USERNAME);
    @Builder.Default private String password = DataGenerator.getRandomString(10, true, true, true);
}

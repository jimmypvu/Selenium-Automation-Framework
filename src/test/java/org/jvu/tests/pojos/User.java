package org.jvu.tests.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.framework.utils.DataType;
import org.framework.utils.RandomDataGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    @Builder.Default private String firstName = RandomDataGenerator.getRandomFor(DataType.FIRSTNAME);
    @Builder.Default private String lastName = RandomDataGenerator.getRandomFor(DataType.LASTNAME);
    @Builder.Default private String email = RandomDataGenerator.getRandomFor(DataType.EMAIL);
    @Builder.Default private String address = RandomDataGenerator.getRandomFor(DataType.ADDRESS);
    @Builder.Default private String city = RandomDataGenerator.getRandomFor(DataType.CITY);
    @Builder.Default private String state = "California";
    @Builder.Default private String country = "United States";
    @Builder.Default private String zip = RandomDataGenerator.getRandomNumber(5);
    @Builder.Default private String username = RandomDataGenerator.getRandomFor(DataType.USERNAME);
    @Builder.Default private String password = RandomDataGenerator.genString(10, true, true, true);
}

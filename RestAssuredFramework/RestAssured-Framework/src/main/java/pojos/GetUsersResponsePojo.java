package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties("true")
public class GetUsersResponsePojo {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    @Getter
    @ToString
    public class Address {
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;

        @Getter
        @ToString
        public class Geo {
            private String lat;
            private String lng;
        }
    }

    @Getter
    @ToString
    public class Company {
        private String name;
        private String catchPhrase;
        private String bs;
    }
}
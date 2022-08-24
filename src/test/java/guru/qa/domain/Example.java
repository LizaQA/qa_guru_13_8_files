package guru.qa.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Example {
    private String product;
    private Integer version;
    @JsonProperty("demo")
    private Boolean isDemo;
    private Person person;

    public String getProduct() {
        return product;
    }

    public Integer getVersion() {
        return version;
    }

    public Boolean getDemo() {
        return isDemo;
    }

    public Person getPerson() {
        return person;
    }

    public static class Person {
        private Integer id;
        private String name;
        private Phones phones;
        private List<String> email;
        @JsonProperty("registered")
        private Boolean isRegistered;
        private List<EmergencyContact> emergencyContacts;

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Phones getPhones() {
            return phones;
        }

        public List<String> getEmail() {
            return email;
        }

        public Boolean getRegistered() {
            return isRegistered;
        }

        public List<EmergencyContact> getEmergencyContacts() {
            return emergencyContacts;
        }

        public static class Phones {
            private String home;
            private String mobile;

            public String getHome() {
                return home;
            }

            public String getMobile() {
                return mobile;
            }
        }
        public static class EmergencyContact {
            private String name;
            private String phone;
            private String relationship;

            public String getName() {
                return name;
            }

            public String getPhone() {
                return phone;
            }

            public String getRelationship() {
                return relationship;
            }
        }
    }
}

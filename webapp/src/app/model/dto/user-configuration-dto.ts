class UserConfigurationDto {
  id: string;
  pesel: string;
  password: string;
  address: {
    town: string;
    postalCode: string;
    street: string;
    number: string;
  }
}

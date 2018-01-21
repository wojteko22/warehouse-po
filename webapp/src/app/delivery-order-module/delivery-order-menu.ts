import {MdIconsDefinitions} from "../md-icons-definitions";

const mdIcons = MdIconsDefinitions;

export const deliveryOrderMenu = [
  {
    icon: mdIcons.start_page,
    optionName: "Strona główna",
    routerLink: ""
  },
  {
    icon: mdIcons.start_page,
    optionName: "Zamówienia dostaw",
    routerLink: "/deliveryOrder/all"
  },
  {
    icon: mdIcons.start_page,
    optionName: "Zlecenia przyjęcia",
    routerLink: "/deliveryOrder/acceptanceOrders"
  }
];

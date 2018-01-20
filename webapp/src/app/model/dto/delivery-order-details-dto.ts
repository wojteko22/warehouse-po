import {DeliveryOrderPositionDto} from "./delivery-order-position-dto";

export interface DeliveryOrderDetailsDto {
  orderNumber: string,
  provider: string,
  positions: DeliveryOrderPositionDto[]
}

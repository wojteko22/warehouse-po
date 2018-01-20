import {CommodityDto} from "./commodity-dto";

export interface DeliveryOrderPositionDto {
  commodity: CommodityDto,
  quantity: string
}

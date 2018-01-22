import {CommodityDto} from "./commodity-dto";

export interface AcceptanceOrderPositionDto {
  commodity: CommodityDto,
  quantityToAccept: number,
  fraction: number
}

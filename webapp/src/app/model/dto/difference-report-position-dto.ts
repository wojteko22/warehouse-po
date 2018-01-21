import {CommodityDto} from "./commodity-dto";

export interface DifferenceReportPositionDto {
  id: string,
  commodity: CommodityDto,
  orderedQuantity: number,
  deliveredQuantity: number,
}

import {AcceptanceOrderPositionDto} from "./acceptance-order-position-dto";

export interface AcceptanceOrderDetailsDto {
  status: number,
  positions: AcceptanceOrderPositionDto[]
}

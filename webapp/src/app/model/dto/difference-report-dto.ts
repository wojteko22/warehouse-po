import {DifferenceReportPositionDto} from "./difference-report-position-dto";

export interface DifferenceReportDto {
  deliveryOrderNumber: string,
  differenceReportPositions: DifferenceReportPositionDto[]
}

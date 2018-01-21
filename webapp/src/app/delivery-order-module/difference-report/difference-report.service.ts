import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {CommodityDto} from "../../model/dto/commodity-dto";
import {DifferenceReportDto} from "../../model/dto/difference-report-dto";

@Injectable()
export class DifferenceReportService {
  private baseUrl = environment.apiUrl + "/difference-reports";

  constructor(private http: HttpClient) {
  }

  getAvailableCommodities(orderNumber: String): Promise<CommodityDto[]> {
    return this.http.get<CommodityDto[]>(this.baseUrl + '/' + orderNumber + '/available-commodities').toPromise()
  }

  createDefaultReport(orderId: string): Promise<string> {
    const params = new HttpParams().set('deliveryOrderId', orderId);
    return this.http.post<string>(this.baseUrl, params).toPromise()
  }

  getReport(id: string): Promise<DifferenceReportDto> {
    return this.http.get<DifferenceReportDto>(this.baseUrl + '/' + id).toPromise()
  }

  addCommodity(reportId: string, commodityId: number) {
    const params = new HttpParams().set('commodityId', commodityId.toString());
    return this.http.post(this.baseUrl + '/' + reportId + '/positions', params).toPromise();
  }
}

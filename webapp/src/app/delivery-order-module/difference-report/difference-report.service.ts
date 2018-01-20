import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {CommodityDto} from "../../model/dto/commodity-dto";

@Injectable()
export class DifferenceReportService {
  private baseUrl = environment.apiUrl + "/difference-reports";

  constructor(private http: HttpClient) {
  }

  getAvailableCommodities(orderNumber: String): Promise<CommodityDto[]> {
    return this.http.get<CommodityDto[]>(this.baseUrl + '/' + orderNumber + '/available-commodities').toPromise()
  }


}

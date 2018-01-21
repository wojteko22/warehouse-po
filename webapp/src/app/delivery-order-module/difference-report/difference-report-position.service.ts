import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Injectable()
export class DifferenceReportPositionService {
  private baseUrl = environment.apiUrl + "/difference-report-positions";

  constructor(private http: HttpClient) {
  }

  savePosition(id: string, deliveredQuantity: number): Promise<Object> {
    const params = new HttpParams().set('deliveredQuantity', deliveredQuantity.toString());
    return this.http.patch(this.baseUrl + '/' + id, params).toPromise();
  }
}

import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Injectable()
export class DifferenceReportPositionService {
  private baseUrl = environment.apiUrl + "/difference-report-positions";

  constructor(private http: HttpClient){
  }

}

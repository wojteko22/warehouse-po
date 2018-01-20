
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Injectable()
export class DifferenceReportService {
  private baseUrl = environment.apiUrl + "/difference-reports";

  constructor(private http: HttpClient){
  }


}

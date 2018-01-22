import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Injectable()
export class RegistrationDocumentService {
  private baseUrl = environment.apiUrl + "/registration-documents";

  constructor(private http: HttpClient) {
  }

  generate(acceptanceOrderId: number): Promise<Object> {
    const params = new HttpParams().set('acceptanceOrderId', acceptanceOrderId.toString());
    return this.http.post(this.baseUrl, params).toPromise()
  }
}

import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class DeliveryOrderService {
  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) {
  }
}

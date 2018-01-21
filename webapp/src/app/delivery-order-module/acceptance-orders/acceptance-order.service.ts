import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {DeliveryOrderDto} from "../../model/dto/delivery-order-dto";

@Injectable()
export class AcceptanceOrderService {
  private baseUrl = environment.apiUrl + "/acceptance-orders";

  constructor(private http: HttpClient) {
  }

  getUnhandled(): Promise<DeliveryOrderDto[]> {
    return this.http.get<DeliveryOrderDto[]>(this.baseUrl).toPromise()
  }
}

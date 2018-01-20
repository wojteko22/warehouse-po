import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {DeliveryOrderDto} from "../model/dto/delivery-order-dto";

@Injectable()
export class DeliveryOrderService {
  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) {
  }

  getAllDeliveryOrders(): DeliveryOrderDto[] {
    // return this.http.get<DeliveryOrderDto[]>(this.baseUrl + '/delivery-orders').toPromise()
    return [
      {orderNumber:"1", provider: "kfal", predictedDeliveryDate:"fdklfa"},
      {orderNumber:"2", provider: "kfal", predictedDeliveryDate:"fdklfa"},
      {orderNumber:"3", provider: "kfal", predictedDeliveryDate:"fdklfa"},
      {orderNumber:"4", provider: "kfdfa", predictedDeliveryDate:"fdklfa"},
      ]
  }
}

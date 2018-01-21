import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {DeliveryOrderDto} from "../model/dto/delivery-order-dto";
import {DeliveryOrderDetailsDto} from "../model/dto/delivery-order-details-dto";

@Injectable()
export class DeliveryOrderService {
  private baseUrl = environment.apiUrl + "/delivery-orders";

  constructor(private http: HttpClient) {
  }

  getAllDeliveryOrders(): Promise<DeliveryOrderDto[]> {
    return this.http.get<DeliveryOrderDto[]>(this.baseUrl).toPromise()
  }

  getDeliveryOrderDetail(orderId: string): Promise<DeliveryOrderDetailsDto> {
    return this.http.get<DeliveryOrderDetailsDto>(this.baseUrl + "/" + orderId).toPromise()
  }
}

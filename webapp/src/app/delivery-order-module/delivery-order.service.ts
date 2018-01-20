import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {DeliveryOrderDto} from "../model/dto/delivery-order-dto";
import {DeliveryOrderDetailsDto} from "../model/dto/delivery-order-details-dto";

@Injectable()
export class DeliveryOrderService {
  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) {
  }

  getAllDeliveryOrders(): Promise<DeliveryOrderDto[]> {
    return this.http.get<DeliveryOrderDto[]>(this.baseUrl + '/delivery-orders').toPromise()
  }

  getDeliveryOrderDetail(orderNumber: string): Promise<DeliveryOrderDetailsDto> {
    return this.http.get<DeliveryOrderDetailsDto>(this.baseUrl + "/delivery-orders/" + orderNumber).toPromise()
  }
}

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

  getAllDeliveryOrders(): DeliveryOrderDto[] {
    // return this.http.get<DeliveryOrderDto[]>(this.baseUrl + '/delivery-orders').toPromise()
    return [
      {orderNumber: "1", provider: "kfal", predictedDeliveryDate: "fdklfa"},
      {orderNumber: "2", provider: "kfal", predictedDeliveryDate: "fdklfa"},
      {orderNumber: "3", provider: "kfal", predictedDeliveryDate: "fdklfa"},
      {orderNumber: "4", provider: "kfdfa", predictedDeliveryDate: "fdklfa"},
    ]
  }

  getDeliveryOrderDetail(orderNumber: string): DeliveryOrderDetailsDto {
    return {
      orderNumber: orderNumber,
      provider: "maszyno hurt",
      positions: [
        {
          commodity: {
            commodityCode: "nbmd dak",
            commodityName: "pasza dla kur",
            measure: "kg",
            producer: "brojlery gurą"
          },
          quantity: "15"
        },
        {
          commodity: {
            commodityCode: "bakaraka",
            commodityName: "mlekosan",
            measure: "sztuki",
            producer: "sano"
          },
          quantity: "5"
        }
      ]
    }
  }
}

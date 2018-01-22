import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {AcceptanceOrderDto} from "../../model/dto/acceptance-order-dto";
import {AcceptanceOrderDetailsDto} from "../../model/dto/acceptance-order-details-dto";

@Injectable()
export class AcceptanceOrderService {
  private baseUrl = environment.apiUrl + "/acceptance-orders";

  constructor(private http: HttpClient) {
  }

  getUnhandled(): Promise<AcceptanceOrderDto[]> {
    return this.http.get<AcceptanceOrderDto[]>(this.baseUrl).toPromise()
  }

  get(id: number): Promise<AcceptanceOrderDetailsDto> {
    return this.http.get<AcceptanceOrderDetailsDto>(this.baseUrl + '/' + id).toPromise()
  }
}

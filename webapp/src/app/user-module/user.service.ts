import {Injectable} from "@angular/core";
import {HttpClient} from '@angular/common/http';
import {environment} from "../../environments/environment";

@Injectable()
export class UserService {
  private baseUrl = environment.apiUrl + 'user';

  constructor(private http: HttpClient) {
  }

  getUserPermissions(): Promise<string[]> {
    return this.http.get<string[]>(this.baseUrl + "/permissions").toPromise();
  }

  postUserData(userDto: UserRegisterDto) {
    return this.http.post(this.baseUrl + "/add", userDto).toPromise()
  }

  getIsExistEmail(userMail: string): Promise<boolean> {
    return this.http.get<boolean>(this.baseUrl + "/exist/" + userMail).toPromise()
  }
}

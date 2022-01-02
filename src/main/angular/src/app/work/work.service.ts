import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {IWorkDTO} from "../entity/IWorkDTO";
import {ICreateDTO} from "../entity/ICreateDTO";
import {constants} from "../common/constants";


@Injectable({
  providedIn: 'root'
})
export class WorkService {
  public API: string = "http://localhost:8088/api/v1/works/";
  public pageSize: number = 10;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {
  }

  getAllWork(index: number, pageSize: number, sortBy: String, desc: String): Observable<IWorkDTO[]> {
    return this.http.get<IWorkDTO[]>(this.API + '?pageNo=' + index + "&pageSize=" + pageSize + "&sortBy=" + sortBy + "&desc=" + desc)
  }

  getAllWorkNotPagination(): Observable<IWorkDTO[]> {
    return this.http.get<IWorkDTO[]>(this.API)
  }

  createWorkDTO(work: ICreateDTO): Observable<ICreateDTO> {
    return this.http.post<ICreateDTO>(this.API + '/insert', JSON.stringify(work), this.httpOptions)
  }
}

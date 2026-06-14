import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Page } from '../models/page.model';

export abstract class CrudService<T> {
  protected abstract apiUrl: string;

  constructor(protected http: HttpClient) {}

  getAll(page: number = 0, size: number = 20): Observable<Page<T>> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get<Page<T>>(this.apiUrl, { params });
  }

  getById(id: number): Observable<T> {
    return this.http.get<T>(`${this.apiUrl}/${id}`);
  }

  create(data: T): Observable<T> {
    return this.http.post<T>(this.apiUrl, data);
  }

  update(id: number, data: T): Observable<T> {
    return this.http.put<T>(`${this.apiUrl}/${id}`, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
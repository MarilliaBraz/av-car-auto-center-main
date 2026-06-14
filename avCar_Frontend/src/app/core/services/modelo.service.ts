import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CrudService } from './crud.service';
import { Modelo } from '../models/modelo.model';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class ModeloService extends CrudService<Modelo> {
  protected apiUrl = `${environment.apiUrl}/modelos`;

  constructor(http: HttpClient) {
    super(http);
  }
}
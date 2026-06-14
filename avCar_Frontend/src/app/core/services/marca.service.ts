import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CrudService } from './crud.service';
import { Marca } from '../models/marca.model';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class MarcaService extends CrudService<Marca> {
  protected apiUrl = `${environment.apiUrl}/marcas`;

  constructor(http: HttpClient) {
    super(http);
  }
}
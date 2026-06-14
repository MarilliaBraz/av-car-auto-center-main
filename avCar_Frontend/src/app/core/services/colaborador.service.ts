import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CrudService } from './crud.service';
import { Colaborador } from '../models/colaborador.model';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class ColaboradorService extends CrudService<Colaborador> {
  protected apiUrl = `${environment.apiUrl}/colaboradores`;

  constructor(http: HttpClient) {
    super(http);
  }
}
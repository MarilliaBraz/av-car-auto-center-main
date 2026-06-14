import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CrudService } from './crud.service';
import { FuncaoColaborador } from '../models/funcao-colaborador.model';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class FuncaoColaboradorService extends CrudService<FuncaoColaborador> {
  protected apiUrl = `${environment.apiUrl}/funcoes-colaborador`;

  constructor(http: HttpClient) {
    super(http);
  }
}
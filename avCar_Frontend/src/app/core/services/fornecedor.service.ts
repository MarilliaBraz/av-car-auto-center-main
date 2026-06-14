import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CrudService } from './crud.service';
import { Fornecedor } from '../models/fornecedor.model';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class FornecedorService extends CrudService<Fornecedor> {
  protected apiUrl = `${environment.apiUrl}/fornecedores`;

  constructor(http: HttpClient) {
    super(http);
  }
}
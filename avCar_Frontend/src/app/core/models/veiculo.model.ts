export interface Veiculo {
  id?: number;
  placa: string;
  anoFabricacao: number;
  cor: string;
  idModelo: number;
  nomeModelo?: string;
  nomeMarca?: string;
  active: true;
}
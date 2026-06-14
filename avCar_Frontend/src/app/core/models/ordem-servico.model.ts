export type OrdemServicoStatus = 'ORCAMENTO' | 'EXECUCAO' | 'PAGAMENTO' | 'FINALIZADO';

export interface OrdemServico {
  id?: number;
  numero?: string;
  dataAbertura: string;
  dataFechamento?: string;
  status: OrdemServicoStatus;
  valorTotal: number;
  observacoes: string;
  idVeiculo: number;
  placa?: string;
  active: true;
}
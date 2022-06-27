package com.ceiba.factura.controlador;


public class RespuestaFactura {
    private Long valor;

    public RespuestaFactura() {
    }

    public RespuestaFactura(Long valor) {
        this.valor = valor;
    }

    public Long getValor() {
        return valor;
    }
}

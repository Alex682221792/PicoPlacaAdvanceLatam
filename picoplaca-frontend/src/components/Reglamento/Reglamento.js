import React from 'react';
import ButtonHome from '../commons/ButtonHome/ButtonHome';

class Reglamento extends React.Component {
  state={
    dias:[],
    reglamentos:[]
  }

  constructor () {
    super();
    this.getReglamentos = this.getReglamentos.bind(this);
  }

  componentDidMount(){
    this.getCatalogoValues("DAY", this.setDays);
    this.getReglamentos();
  }

  getCatalogoValues(codigoCatalogo, setTargetStateFn){
    fetch('http://localhost:8080/api/catalogo/findByCodigo/'+codigoCatalogo)
    .then(res=>res.json())
    .then((data)=>{
      let self = this;
      setTargetStateFn(self, data[0].valores);
    })
    .catch(console.log)
  }


  getReglamentos(){
    fetch('http://localhost:8080/api/reglamento/getAllReglamentosActives/')
    .then(res=>res.json())
    .then((data)=>{
      this.setState({reglamentos: data});
      console.log("activos:",this.state.reglamentos);
    })
    .catch(console.log)
  }

  setDays(self, dias){
    self.setState({dias:dias});
  }

  render () {
    return (
      <div className="col-sm-12">
      <ButtonHome />
      <div className="p-4 col-md-10 offset-md-1">
        <div className="card">
            <div className="card-header">Reglamento (Pico y Placa)</div>
            <div className="card-body p-2">
              <ListarReglamento reglamentos={this.state.reglamentos}/>
          </div>
        </div>
        </div>
      </div>
    )
  }
}

const FormReglamento = ({dias}) => (
  <div className='text-white row'>
    <div className="input-group row form-group col-sm-12">
      <div className="input-group-prepend">
        <label htmlFor="placa" className="input-group-text">Día</label>
      </div>
        <select className="custom-select" id="marca">
          <option defaultValue disabled>Seleccione...</option>
          {dias.map((dia)=>
            (<option key={dia.id} value={dia.id}>{dia.descripcion}</option>)
          )}
        </select>
    </div>
    <div className="input-group row form-group col-sm-12 col-md-6 mr-1">
      <div className="input-group-prepend">
        <label htmlFor="horaInicio" className="input-group-text">Hora inicio</label>
      </div>
        <input type="time" className="form-control" id="horaInicio" placeholder="horaInicio"/>
    </div>
    <div className="input-group row form-group col-sm-12 col-md-6">
      <div className="input-group-prepend">
        <label htmlFor="horaFin" className="input-group-text">Hora fin</label>
      </div>
        <input type="time" className="form-control" id="horaFin" placeholder="horaFin"/>
    </div>
    <div className="input-group row form-group col-sm-12">
      <div className="input-group-prepend">
        <label htmlFor="horaFin" className="input-group-text">Hora fin</label>
      </div>
        <input type="number" max="9" min="0" className="form-control"
          id="digitoPlaca" placeholder="Ingrese último digito de placa"/>
        <button className="input-group-button btn btn-primary">Añadir</button>
    </div>
  </div>
)

const ListarReglamento = ({reglamentos}) =>(
  <div className="col-md-8 offset-md-2">
  {reglamentos.map((regla)=>
    (
      <div className="col-sm-12 row bg-light text-dark mb-1">
        <span className="col-sm-12 col-md-6"><strong>Dia: </strong>{regla.catalogoValorDia.descripcion}</span>
        <span className="col-sm-12 col-md-6"><strong>Dígitos: </strong>{
          regla.detalles.map((digito, key)=>(<li key={digito.id["digitoPlaca"]}>{digito.id["digitoPlaca"]}</li>))
        }</span>
        <span className="col-sm-12 col-md-6"><strong>Desde: </strong>{regla.horaInicio}</span>
        <span className="col-sm-12 col-md-6"><strong>Hasta: </strong>{regla.horaFin}</span>
      </div>
    )
  )}
  </div>
)
export default Reglamento;

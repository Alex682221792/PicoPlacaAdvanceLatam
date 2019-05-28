import React from 'react';
import ButtonHome from '../commons/ButtonHome/ButtonHome';
import Modal from 'react-bootstrap/Modal'
import Button from 'react-bootstrap/Button'
import Alert from 'react-bootstrap/Alert'

class ConsultaPicoPlaca extends React.Component {
  state={
    showMensajeError:false,
    showDataVehiculo:false,
    showDataPicoPlaca:false,
    resultVehiculo:{
      placa:"",
      chasis:"",
      capacidad:"",
      catalogoValorColor:{},
      catalogoValorMarca:{},
      catalogoValorModelo:{}
    },
    restulPicoPlaca:[],
    resultMensajeError:"",
    tipoMensaje:"info",
    consulta:{
      placa:"",
      fecha:""
    }
  }
  constructor () {
    super();
    this.handleCloseDataVehiculo = this.handleCloseDataVehiculo.bind(this);
    this.handleCloseDataPicoPlaca = this.handleCloseDataPicoPlaca.bind(this);
    this.handleCloseMensajeError = this.handleCloseMensajeError.bind(this);
  }

  handleCloseDataVehiculo() {
    this.setState({ showDataVehiculo: false });
  }

  handleCloseDataPicoPlaca() {
    this.setState({ showDataPicoPlaca: false });
  }

  handleCloseMensajeError() {
    this.setState({ showMensajeError: false });
  }

  render () {
    return (
      <div className="col-sm-12">
        <ButtonHome />
        <div className="p-4 col-md-10 offset-md-1">
          <div className="card">
              <div className="card-header">Consultar Pico y Placa</div>
              <div className="card-body p-5">
              <FormBuscarPicoPlaca self={this}/>
              <ModalVehiculo infoVehiculo={this.state.resultVehiculo} self={this}/>
              <ModalReglamento reglamentos={this.state.restulPicoPlaca} self={this}/>
            </div>
          </div>
          <br/>
          <MensajeError mensaje={this.state.resultMensajeError} self={this}
          tipo={this.state.tipoMensaje}/>
        </div>
      </div>
    )
  }

  onChangeFormConsulta(event){
    let vehiculoTemp = this.state.consulta;
    vehiculoTemp[event.target.id]=event.target.value;
    this.setState({consulta:vehiculoTemp});
  }

  searchPlacaAndDay(datos, event){
    if(this.validateForm()){
      fetch('http://localhost:8080/api/reglamento/findByPlacaAndDia/'+datos.placa+"/"+datos.fecha)
      .then(res=>res.json())
      .then((data)=>{
        let self = this;
        if(data.messageError==null){
          if(data.auto!=null){
            self.setState({showDataVehiculo: true})
            self.setState({resultVehiculo: data.auto})
          }else {
            if(data.reglametos!=null && data.reglametos.length>0){
              self.setState({showDataPicoPlaca: true})
              self.setState({restulPicoPlaca: data.reglametos})
            }else {
              self.setState({
                showMensajeError: true,
                resultMensajeError: "No hemos encontrado un vehículo con las placas ingresadas",
                tipoMensaje:"warning"
              })
            }
          }
        }else {
          self.setState({
            showMensajeError: true,
            resultMensajeError: data.messageError,
            tipoMensaje:"danger"
          })
        }
      })
      .catch(console.log)
    }else {
      this.setState({
        showMensajeError: true,
        resultMensajeError: "Datos incompletos o invalidos en el formulario, por favor revise.",
        tipoMensaje:"warning"
      })
    }
  }

  validateForm(){
    let datos = this.state.consulta;
    if(this.isEmptyInputValue(datos.placa)){return false;}
    if(this.isEmptyInputValue(datos.fecha)){return false;}
    return true;
  }

  isEmptyInputValue(field){
    return field==null || field==undefined || field=="";
  }



}
const FormBuscarPicoPlaca = ({self}) => (
  <div className='text-white '>
    <div className="input-group row form-group">
      <div className="input-group-prepend">
        <label htmlFor="placa" className="input-group-text">Ingresa la placa del vehículo</label>
      </div>
        <input type="text" className="form-control" id="placa" placeholder="Placa"
        value={self.state.consulta.placa} onChange={evt => self.onChangeFormConsulta(evt)}/>
      <div className="input-group-prepend">
        <label htmlFor="fecha" className="input-group-text">Fecha y Hora</label>
      </div>
      <input type="datetime-local" className="form-control" id="fecha" placeholder="Fecha y hora"
        value={self.state.consulta.fecha} onChange={evt => self.onChangeFormConsulta(evt)}/>
      <button className="input-group-button btn btn-primary" onClick={self.searchPlacaAndDay.bind(self, self.state.consulta)}>Buscar
      </button>
    </div>
  </div>
);

const ModalVehiculo = ({infoVehiculo, self}) => (
  <Modal show={self.state.showDataVehiculo} onHide={self.handleCloseDataVehiculo}>
    <Modal.Header closeButton>
      <Modal.Title>Información de Vehiculo</Modal.Title>
    </Modal.Header>
    <Modal.Body>
      <div className="col-sm-12 row">
        <span className="col-sm-12 col-md-6"><strong>Placa: </strong>{infoVehiculo.placa}</span>
        <span className="col-sm-12 col-md-6"><strong>Chasis: </strong>{infoVehiculo.chasis}</span>
        <span className="col-sm-12 col-md-6"><strong>Capacidad: </strong>{infoVehiculo.capacidad}</span>
        <span className="col-sm-12 col-md-6"><strong>Color: </strong>{infoVehiculo.catalogoValorColor.descripcion}</span>
        <span className="col-sm-12 col-md-6"><strong>Marca: </strong>{infoVehiculo.catalogoValorMarca.descripcion}</span>
        <span className="col-sm-12 col-md-6"><strong>Modelo: </strong>{infoVehiculo.catalogoValorModelo.descripcion}</span>
        <span className="col-sm-12"><strong>Tu vehículo puede circular</strong></span>
      </div>
    </Modal.Body>
    <Modal.Footer>
      <Button variant="secondary" onClick={self.handleCloseDataVehiculo}>
        Cerrar
      </Button>
    </Modal.Footer>
  </Modal>
)

const ModalReglamento = ({reglamentos, self}) => (
  <Modal show={self.state.showDataPicoPlaca} onHide={self.handleCloseDataPicoPlaca}>
    <Modal.Header closeButton>
      <Modal.Title>Información de Pico y Placa</Modal.Title>
    </Modal.Header>
    <Modal.Body>
    <div className="col-sm-12 row">
      <span>Tu vehículo no puede circular los siguientes días: </span>
    </div>
    {reglamentos.map((regla)=>
      (
        <div className="col-sm-12 row">
          <span className="col-sm-12"><strong>Dia: </strong>{regla.catalogoValorDia.descripcion}</span>
          <span className="col-sm-12 col-md-6"><strong>Desde: </strong>{regla.horaInicio}</span>
          <span className="col-sm-12 col-md-6"><strong>Hasta: </strong>{regla.horaFin}</span>
        </div>
      )
    )}
    </Modal.Body>
    <Modal.Footer>
      <Button variant="secondary" onClick={self.handleCloseDataPicoPlaca}>
        Cerrar
      </Button>
    </Modal.Footer>
  </Modal>
)

const MensajeError = ({mensaje, tipo, self}) => (
  <Alert show={self.state.showMensajeError} variant={tipo}>
      <Alert.Heading>Aviso!</Alert.Heading>
      <p>
        {mensaje}
      </p>
      <hr />
      <div className="d-flex justify-content-end">
        <Button onClick={self.handleCloseMensajeError} variant="outline-dark">
          Cerrar
        </Button>
      </div>
    </Alert>
);

export default ConsultaPicoPlaca;

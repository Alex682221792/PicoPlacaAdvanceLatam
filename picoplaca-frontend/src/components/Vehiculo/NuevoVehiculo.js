import React from 'react';
import ButtonHome from '../commons/ButtonHome/ButtonHome';
import Alert from 'react-bootstrap/Alert'
import Button from 'react-bootstrap/Button'

class NuevoVehiculo extends React.Component {
  state = {
    colores:[],
    modelos:[],
    marcas:[],
    tipoMensaje:"info",
    resultMensajeError:"",
    showMensajeError:false,
    vehiculo:{
      placa:"",
      chasis:"",
      capacidad:1,
      color:"",
      marca:"",
      modelo:""
    }
  }

  handleCloseMensajeError() {
    this.setState({ showMensajeError: false });
  }

  constructor () {
    super();
    this.handleCloseMensajeError = this.handleCloseMensajeError.bind(this);
    this.validateForm = this.validateForm.bind(this);
    this.resetForm = this.resetForm.bind(this);
  }

  componentDidMount(){
    this.getCatalogoValues("CLR", this.setColores);
    this.getCatalogoValues("MDL", this.setModelos);
    this.getCatalogoValues("MRC", this.setMarcas);
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

  saveVehiculo(datos, event){
    let self = this;
    if(this.validateForm()){
      fetch('http://localhost:8080/api/auto/save/', {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(datos),
      })
      .then(res=>res.json())
      .then((data)=>{
        console.log("saved-data",datos);
        if(data.mensajeError!=null){
          self.setState({
            resultMensajeError:data.mensajeError,
            showMensajeError: true,
            tipoMensaje:"danger"
          })
        }else {
          self.setState({
            resultMensajeError:"Vehículo guardado correctamente!",
            showMensajeError: true,
            tipoMensaje:"success"
          })
        }
      })
      .catch(console.log);
    }
    else{
      this.setState({
        resultMensajeError:"Datos incompletos o invalidos en el formulario, por favor revise.",
        showMensajeError: true,
        tipoMensaje:"warning"
      })
    }
  }

  resetForm(){
    this.setState({
      vehiculo:{
        placa:"",
        chasis:"",
        capacidad:1,
        color:"",
        marca:"",
        modelo:""
      }
    });
  }

  validateForm(){
    let datos = this.state.vehiculo;
    if(this.isEmptyInputValue(datos.placa)){return false;}
    if(this.isEmptyInputValue(datos.chasis)){return false;}
    if(this.isEmptyInputValue(datos.capacidad)){return false;}
    if(this.isEmptySelectValue(datos.color)){return false;}
    if(this.isEmptySelectValue(datos.marca)){return false;}
    if(this.isEmptySelectValue(datos.modelo)){return false;}
    return true;
  }

  isEmptyInputValue(field){
    return field==null || field==undefined || field=="";
  }

  isEmptySelectValue(field){
    return field=="0" || field==0;
  }

  setColores(self, colores){
    self.setState({colores:colores});
  }

  setModelos(self, modelos){
    self.setState({modelos:modelos});
  }

  setMarcas(self, marcas){
    self.setState({marcas:marcas});
  }

  onChangeFormVehiculo(event){
    let vehiculoTemp = this.state.vehiculo;
    vehiculoTemp[event.target.id]=event.target.value;
    this.setState({vehiculo:vehiculoTemp});
  }

  render () {
    return (
      <div className="col-sm-12">
        <ButtonHome />
        <div className="p-4 col-md-10 offset-md-1">
          <div className="card">
            <div className="card-header">Nuevo Vehículo</div>
            <div className="card-body p-5">
              <FormVehiculo colores={this.state.colores}
              marcas={this.state.marcas} modelos={this.state.modelos} self={this}/>
            </div>
            <br/>
            <MensajeAviso mensaje={this.state.resultMensajeError} self={this}
            tipo={this.state.tipoMensaje}/>
          </div>
        </div>
      </div>
    )
  }
}

const FormVehiculo = ({colores, marcas, modelos, self}) => (
<div className='text-white '>
  <div className="input-group row form-group">
    <div className="input-group-prepend">
      <label htmlFor="placa" className="input-group-text">Placa</label>
    </div>
      <input type="text" className="form-control" id="placa" placeholder="Placa"
       value={self.state.vehiculo.placa} onChange={evt => self.onChangeFormVehiculo(evt)}/>
  </div>
  <div className="input-group row form-group">
    <div className="input-group-prepend">
      <label htmlFor="chasis" className="input-group-text">Chasis</label>
    </div>
      <input type="text" className="form-control" id="chasis" placeholder="Chasis"
       value={self.state.vehiculo.chasis} onChange={evt => self.onChangeFormVehiculo(evt)}/>
  </div>
  <div className="input-group row form-group">
    <div className="input-group-prepend">
      <label htmlFor="capacidad" className="input-group-text">Capacidad</label>
    </div>
      <input type="number" min="1" className="form-control" id="capacidad" placeholder="Capacidad"
       value={self.state.vehiculo.capacidad} onChange={evt => self.onChangeFormVehiculo(evt)}/>
  </div>
  <div className="input-group row form-group">
    <div className="input-group-prepend">
      <label className="input-group-text" htmlFor="color">Color</label>
    </div>
    <select className="custom-select" id="color"
     value={self.state.vehiculo.color} onChange={evt => self.onChangeFormVehiculo(evt)}>
    <option value="">Seleccione...</option>
    {colores.map((color)=>
      (<option key={color.id} value={color.id}>{color.descripcion}</option>)
    )}
    </select>
  </div>
  <div className="input-group row form-group">
    <div className="input-group-prepend">
      <label className="input-group-text" htmlFor="marca">Marca</label>
    </div>
    <select className="custom-select" id="marca"
     value={self.state.vehiculo.marca} onChange={evt => self.onChangeFormVehiculo(evt)}>
      <option value="">Seleccione...</option>
      {marcas.map((marca)=>
        (<option key={marca.id} value={marca.id}>{marca.descripcion}</option>)
      )}
    </select>
  </div>
  <div className="input-group row form-group">
    <div className="input-group-prepend">
      <label className="input-group-text" htmlFor="modelo">Modelo</label>
    </div>
    <select className="custom-select" id="modelo"
     value={self.state.vehiculo.modelo} onChange={evt => self.onChangeFormVehiculo(evt)}>
    <option value="">Seleccione...</option>
      {modelos.map((modelo)=>
        (<option key={modelo.id} value={modelo.id}>{modelo.descripcion}</option>)
      )}
    </select>
  </div>
  <div className="row form-group">
    <button className="btn btn-primary mr-1" onClick={self.saveVehiculo.bind(self, self.state.vehiculo)}>Hecho</button>
    <button className="btn btn-light" onClick={self.resetForm}>Limpiar</button>
  </div>
</div>
)

const MensajeAviso = ({mensaje, tipo, self}) => (
  <Alert show={self.state.showMensajeError} variant={tipo}>
      <Alert.Heading>Aviso!</Alert.Heading>
      <p>
        {mensaje}
      </p>
      <hr />
      <div className="d-flex justify-content-end">
        <Button onClick={self.handleCloseMensajeError} variant="outline-success">
          Cerrar
        </Button>
      </div>
    </Alert>
);


export default NuevoVehiculo;

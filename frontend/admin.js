const HOST = "http://localhost:8080";


const loadCustomer = ()=>{
  $.ajax({
    method: "GET",
    url:`${HOST}/carwash/customers`,
    crossDomain: true,
    headers: {
      'Accept': "application/json",
       'Content-type': "application/json",
       'Content-Length': 81,
     },
  })
  .done((response)=> {
    $(document).ready(function () {
      $('#example').DataTable({
          data: response,
          columns: [ 
            { "title":"Customer Name","data": "customerName"},
            { "title":"Customer Phone","data": "customerPhone" },
            { "title":"Car Make","data": "carMake"},
            { "title":"Car Model","data": "carModel" },
            { "title":"Car Plate No","data": "carPlateNo" }
          ]
      });
  });  
  })
  .fail((obj, textStatus) => {
    if ((obj && obj.responseText)) {
      alert(obj.responseText);
      if(obj.status === 409){
        alert('There is a conflict !!!!');
      }
    }
    else{
      alert("general exception");
    } 
  });
}
const btnSearch = document.querySelector("#searchbtn");
btnSearch.addEventListener("click", loadCustomer);


const loadOrder = ()=>{
  $.ajax({
    method: "GET",
    url:`${HOST}/carwash/products`,
    headers: {
      'Accept': "application/json",
       'Content-type': "application/json",
     },
  })
  .done((response)=> {
    $(document).ready(function () {
      $('#example').DataTable({
          data: response,
          columns: [ 
            { "title":"Product Id","data": "productID" },
            { "title":"Product Name","data": "productType" },
            { "title":"Product Price","data": "productPrice"}
          ]
      });
  });  
  })
  .fail((obj, textStatus) => {
    if ((obj && obj.responseText)) {
      alert(obj.responseText);
      if(obj.status === 409){
        alert('There is a conflict !!!!');
      }
    }
    else{
      alert("general exception");
    } 
  });
}

const btnSearcho = document.querySelector("#searchbtnorder");
btnSearcho.addEventListener("click", loadOrder);



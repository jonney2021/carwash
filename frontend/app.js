const HOST = "http://localhost:8080";

const createCustomer = () => {
  // getting the value from the from
  const customerName = document.querySelector("#customerNameInput").value;
  const customerPhone = document.querySelector("#customerPhoneNumber").value;
  const carMake = document.querySelector("#carMake").value;
  const carModel = document.querySelector("#carModel").value;
  const carPlateNo = document.querySelector("#carPlateNo").value;


  $.ajax({
    method: "post",
    url: `${HOST}/carwash/customers`,
    data: JSON.stringify({
      "customerName": customerName,
      "customerPhone": customerPhone,
      "carMake": carMake,
      "carModel": carModel,
      "carPlateNo": carPlateNo
    }),
    crossDomain: true,
    headers: {
     'Accept': "application/json",
      'Content-type': "application/json",
    },
  })
    .done((response) => {
      alert('successful insert');
      $(window).attr('location','order.html'); 
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
};




const btnSubmit = document.querySelector("#submitbtn");
btnSubmit.addEventListener("click", createCustomer);



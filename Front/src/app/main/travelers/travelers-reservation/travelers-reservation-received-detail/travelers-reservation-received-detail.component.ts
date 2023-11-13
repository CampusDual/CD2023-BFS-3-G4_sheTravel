import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material';
import { OFormComponent, OSnackBarConfig, OntimizeService, SnackBarService } from 'ontimize-web-ngx';

@Component({
  selector: 'app-travelers-reservation-received-detail',
  templateUrl: './travelers-reservation-received-detail.component.html',
  styleUrls: ['./travelers-reservation-received-detail.component.css']
})
export class TravelersReservationReceivedDetailComponent implements OnInit {

  @ViewChild('form', { static: true }) form: OFormComponent;

  public id_reservation;
  public id_client_traveler;
  public name_traveler;
  public id_client_host;
  public message;
  public message_answer;
  public id_status;
  public surname_traveler;
  public email_traveler;
  public name_host;
  public surname_host;
  public phonenumber_host;
  public email_host;
  public status_name;
  public reservation_date;
  public message_cancellation;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    protected dialog: MatDialog,
    private ontimizeServiceUsers: OntimizeService,
    private snackBarService: SnackBarService,



  ) { this.ontimizeServiceUsers.configureService(this.ontimizeServiceUsers.getDefaultServiceConfiguration('users')); }



  ngOnInit() {
    this.ontimizeServiceUsers.query({ id_reservation: this.data.id_reservation }, ['id_reservation', 'id_client_traveler', 'id_client_host',
      'message', 'id_status', 'name_traveler', 'surname_traveler', 'email_traveler', 'message_answer', 'name_host', 'surname_host', 'email_host',
      'phonenumber_host', 'status_name', 'reservation_date', 'message_cancellation'], 'reservation').subscribe(
        res => {


          this.id_reservation = res.data[0].id_reservation;
          this.id_client_traveler = res.data[0].id_client_traveler;
          this.name_traveler = res.data[0].name_traveler;
          this.id_client_host = res.data[0].id_client_host;
          this.message = res.data[0].message;
          this.message_answer = res.data[0].message_answer;
          this.message_cancellation = res.data[0].message_cancellation;
          this.id_status = res.data[0].id_status;
          this.surname_traveler = res.data[0].surname_traveler;
          this.email_traveler = res.data[0].email_traveler;
          this.name_host = res.data[0].name_host;
          this.surname_host = res.data[0].surname_host;
          this.email_host = res.data[0].email_host;
          this.phonenumber_host = res.data[0].phonenumber_host;
          this.status_name = res.data[0].status_name;
          this.reservation_date = res.data[0].reservation_date;


        }
      );

  }

  rejectReservation(id_reservation: any) {
    let message_answer = this.form.getComponents().message_answer.getValue();
    let id_status = 2;
    //1
    let parent = this;
    this.ontimizeServiceUsers.update({ id_reservation: id_reservation }, { message_answer: message_answer, id_status: id_status }, 'reservation').subscribe(res => {

      this.dialog.closeAll();

      if (res.code == 0) {
        //2
        parent.data.grid.reloadData();
        // Mostrar el snack-bar con el mensaje de éxito
        const config: OSnackBarConfig = {
          action: 'OK',
          milliseconds: 5000,
          icon: 'check_circle_outline',
          iconPosition: 'left'
        };
        this.snackBarService.open('Respuesta guardada', config);
      } else {
        // Mostrar el snack-bar con el mensaje de error
        this.snackBarService.open(`Error: ${res.message}`, { milliseconds: 5000 });
      }
    });
    

  }

  acceptReservation(id_reservation: any) {
    let message_answer = this.form.getComponents().message_answer.getValue();
    let id_status = 1;
    let parent = this;
    this.ontimizeServiceUsers.update({ id_reservation: id_reservation }, { message_answer: message_answer, id_status: id_status }, 'reservation').subscribe(res => {

      this.dialog.closeAll();

      if (res.code == 0) {
        parent.data.grid.reloadData();
        // Mostrar el snack-bar con el mensaje de éxito
        const config: OSnackBarConfig = {
          action: 'OK',
          milliseconds: 5000,
          icon: 'check_circle_outline',
          iconPosition: 'left'
        };
        this.snackBarService.open('Respuesta guardada', config);
      } else {
        // Mostrar el snack-bar con el mensaje de error
        this.snackBarService.open(`Error: ${res.message}`, { milliseconds: 5000 });
      }

    });
  }

  cancelReservation(id_reservation: any) {
    let message_cancellation = this.form.getComponents().message_cancellation.getValue();
    let id_status = 4;
    //1
    let parent = this;
    this.ontimizeServiceUsers.update({ id_reservation: id_reservation }, { message_cancellation: message_cancellation, id_status: id_status }, 'reservation').subscribe(res => {

      this.dialog.closeAll();

      if (res.code == 0) {
        //2
        parent.data.grid.reloadData();
        // Mostrar el snack-bar con el mensaje de éxito
        const config: OSnackBarConfig = {
          action: 'OK',
          milliseconds: 5000,
          icon: 'check_circle_outline',
          iconPosition: 'left'
        };
        this.snackBarService.open('Respuesta guardada', config);
      } else {
        // Mostrar el snack-bar con el mensaje de error
        this.snackBarService.open(`Error: ${res.message}`, { milliseconds: 5000 });
      }
    });  
  }

}



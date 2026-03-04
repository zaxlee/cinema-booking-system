import { Injectable } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ErrorService {

  handleError(error: HttpErrorResponse): string {

    if (error.error?.message) {
      return error.error.message;
    }

    if (error.status === 409) {
      return "Seat is already booked.";
    }

    if (error.status === 403) {
      return "You are not authorized to perform this action.";
    }

    if (error.status === 401) {
      return "Session expired. Please login again.";
    }

    if (error.status === 500) {
      return "Internal server error.";
    }

    return "Something went wrong.";
  }

}

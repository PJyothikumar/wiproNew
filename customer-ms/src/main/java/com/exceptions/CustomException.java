package com.exceptions;

public class CustomException extends Exception {

	public CustomException(String message) {
		super(message);
	}

	public static class InvalidCredentialsException extends RuntimeException {
		public InvalidCredentialsException(String message) {
			super(message);
		}
	}

	public static class RoomNotFoundException extends RuntimeException {
		public RoomNotFoundException(String message) {
			super(message);
		}
	}

	public static class BookingNotFoundException extends RuntimeException {
		public BookingNotFoundException(String message) {
			super(message);
		}
	}

	public static class CustomerNotFoundException extends CustomException {
		public CustomerNotFoundException(String message) {
			super(message);
		}
	}

	public static class RoomAlreadyBookedException extends RuntimeException {
		public RoomAlreadyBookedException(String message) {
			super(message);
		}
	}

	public static class InvalidBookingDetailsException extends RuntimeException {
		public InvalidBookingDetailsException(String message) {
			super(message);
		}
	}

	public static class RoomServiceException extends RuntimeException {
		public RoomServiceException(String message) {
			super(message);
		}
	}

	public static class NoRoomsFoundException extends RuntimeException {
		public NoRoomsFoundException(String message) {
			super(message);
		}
	}

}

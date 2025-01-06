const NO_AVAILABLE_ROOMS_OF_GIVEN_TYPE = "no.available.rooms.of.given.type";
const END_DATE_CANNOT_BE_BEFORE_START_DATE =
    "end.date.cannot.be.before.start.date";

const FALLBACK_ERROR_MESSAGE = "Kunde inte genomföra bokning";

export const getErrorMessage = (errorCode: string) => {
    switch (errorCode) {
        case NO_AVAILABLE_ROOMS_OF_GIVEN_TYPE:
            return "Det finns inga tillgängliga rum av den valda typen.";
        case END_DATE_CANNOT_BE_BEFORE_START_DATE:
            return "Slutdatum får inte vara före startdatum.";
        default:
            return FALLBACK_ERROR_MESSAGE;
    }
};

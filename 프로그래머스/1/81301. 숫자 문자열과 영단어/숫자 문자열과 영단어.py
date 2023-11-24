answer = ""


def convertAlphaToDigit(alpha):
    if alpha == "zero":
        return "0"
    elif alpha == "one":
        return "1"
    elif alpha == "two":
        return "2"
    elif alpha == "three":
        return "3"
    elif alpha == "four":
        return "4"
    elif alpha == "five":
        return "5"
    elif alpha == "six":
        return "6"
    elif alpha == "seven":
        return "7"
    elif alpha == "eight":
        return "8"
    elif alpha == "nine":
        return "9"
    else:
        return False


def checkString(str):
    global answer
    temp = ""
    NOT_DIGIT = False

    for s in str:
        if NOT_DIGIT:
            temp += s
            if len(temp) >= 3:
                digit = convertAlphaToDigit(temp)
                if digit != False:
                    answer += digit
                    temp = ""
                    NOT_DIGIT = False

        elif s.isdigit() == False:
            temp += s
            NOT_DIGIT = True

        else:
            answer += s


def solution(str):
    checkString(str)
    return int(answer)
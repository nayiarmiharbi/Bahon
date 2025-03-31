import { atom } from "jotai";

// Load user from localStorage if available
const storedUser = JSON.parse(localStorage.getItem("user")) || null;

export const userAtom = atom(storedUser);

// Create a writable atom that automatically syncs with localStorage
export const userAtomWithPersistence = atom(
  (get) => get(userAtom),
  (get, set, newUser) => {
    set(userAtom, newUser);
    if (newUser) {
      localStorage.setItem("user", JSON.stringify(newUser)); // Save user to localStorage
    } else {
      localStorage.removeItem("user"); // Remove user from localStorage on logout
    }
  }
);
